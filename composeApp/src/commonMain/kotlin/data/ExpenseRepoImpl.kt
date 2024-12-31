package data

import com.exampleApp.db.AppDatabase
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

//Vid 31
//Cid 32, inyeccion de dependencias : private val expenseManager: ExpenseManager
//Vid 52, implementamos database
class ExpenseRepoImpl(
    private val appDatabase: AppDatabase
    ):ExpenseRepository {
        //Vid 52
    private val queries = appDatabase.expensesDbQueries
    //Vid 53
    override  fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {

                Expense(
                    id = it.id,
                    amount = it.amount,
                    category = ExpenseCategory.valueOf(it.categoryName),
                    description = it.description
                )
            }
    }
    //Vid 53
    override fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }
    //Vid 53
    override  fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }
    //Vid 53
    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }

    //Vid 32, lo deja como tarea
    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }

}