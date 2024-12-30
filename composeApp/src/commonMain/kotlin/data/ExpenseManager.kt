package data

import model.Expense
import model.ExpenseCategory

//Vud 30
object  ExpenseManager {

    private  var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.GROCERIES,
            description = "Weekly buy"
        ),
        Expense(
            id = currentId++,
            amount = 40.0,
            category = ExpenseCategory.SNACKS,
            description = "Hommies"
        ),
        Expense(
            id = currentId++,
            amount = 70000.0,
            category = ExpenseCategory.CAR,
            description = "MINI COPPER"
        ),
        Expense(
            id = currentId++,
            amount = 130.0,
            category = ExpenseCategory.PARTY,
            description = "Weekend party"
        ),
        Expense(
            id = currentId++,
            amount = 20.0,
            category = ExpenseCategory.HOUSE,
            description = "Cleaning"
        ),
        Expense(
            id = currentId++,
            amount = 20.0,
            category = ExpenseCategory.OTHER,
            description = "Services"
        )
    )

    //Vid 31
    fun addNewExpense(expense: Expense){
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if(index != -1){
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description

            )
        }
    }

    //Vid 32 ,eliminar

    fun deleteExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        fakeExpenseList.removeAt(index)
    }

    //Vid 31
    fun getCategories():List<ExpenseCategory>{
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER
        )
    }
}