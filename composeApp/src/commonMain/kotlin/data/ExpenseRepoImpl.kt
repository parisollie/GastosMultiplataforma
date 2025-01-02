package data

import com.exampleApp.db.AppDatabase
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import model.Expense
import model.ExpenseCategory
import model.NetworkExpense



//Vid 64,Change ip with your ip after runing server, please see
//https://github.com/gastsail/ktorExpensesApi/tree/master
private const val BASE_URL = "http://192.168.0.47:8080"


//Vid 31
//Cid 32, inyeccion de dependencias : private val expenseManager: ExpenseManager
//Vid 52, implementamos database
class ExpenseRepoImpl(
    private val appDatabase: AppDatabase,
    //Vid 64
    private val httpClient: HttpClient
    ):ExpenseRepository {
        //Vid 52
    private val queries = appDatabase.expensesDbQueries

    //Vid 53
    override suspend fun getAllExpenses(): List<Expense> {
        //sino tengo nada
        return if (queries.selectAll().executeAsList().isEmpty()) {
            //Vid 64
            val networkResponse = httpClient.get("$BASE_URL/expenses").body<List<NetworkExpense>>()
            //Vid 68, si no hay ningun elemento en la base de datos
            if(networkResponse.isEmpty()) return emptyList()
            val expenses = networkResponse.map { networkExpense ->
                Expense(
                    id = networkExpense.id,
                    amount = networkExpense.amount,
                    category = ExpenseCategory.valueOf(networkExpense.categoryName),
                    description = networkExpense.description
                )
            }
            expenses.forEach {
                queries.insert(it.amount, it.category.name, it.description)
            }
            expenses
        } else {
            queries.selectAll().executeAsList().map {
                Expense(
                    //Vid 53
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }
        }
    }
    //Vid 53
    /*override suspend fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {

                Expense(
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }
    }*/

    //Vid 64
    override suspend fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                //Vid 53
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.post("$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }



    //Vid 53
    /*override suspend fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }*/
    //Vid 53
    /*override suspend  fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }*/
    //Vid 64
    override suspend fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                //Vid 53
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.put("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            ))
        }
    }

    //Vid 53
    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }
    //Vid 69,id: Long
    override suspend fun deleteExpense(id: Long) {
        httpClient.delete("$BASE_URL/expenses/${id}")
        //Vid 68
        queries.transaction {
            queries.delete(
                id = id
            )
        }
    }
}