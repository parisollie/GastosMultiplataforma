package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

//Vid 31
//Cid 32, inyeccion de dependencias : private val expenseManager: ExpenseManager
class ExpenseRepoImpl(private val expenseManager: ExpenseManager):ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return ExpenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        ExpenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        ExpenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return ExpenseManager.getCategories()
    }

    //Vid 32, lo deja como tarea
    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }
}