package domain

import model.Expense
import model.ExpenseCategory

//Vid 31 , creamos el repositorio
interface ExpenseRepository {

    fun getAllExpenses():List<Expense>

    fun addExpense(expense: Expense)

    fun editExpense(expense: Expense)

    fun getCategories(): List<ExpenseCategory>

    //Vid 32
    fun deleteExpense(expense: Expense): List<Expense>
}