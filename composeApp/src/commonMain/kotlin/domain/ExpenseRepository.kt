package domain

import model.Expense
import model.ExpenseCategory

//Vid 31 , creamos el repositorio
interface ExpenseRepository {

    //Vid 64, ponemos suspend
    suspend fun getAllExpenses():List<Expense>

    suspend fun addExpense(expense: Expense)

    suspend fun editExpense(expense: Expense)

    fun getCategories(): List<ExpenseCategory>

    //Vid 32 y Vid 68, Vid 69
    suspend fun deleteExpense(id: Long)
   // suspend fun deleteExpense(expense: Expense): List<Expense>
}