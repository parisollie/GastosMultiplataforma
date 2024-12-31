package presentacion

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
//Vid 32
data class  ExpensesUiState(
    val expenses :List<Expense> = emptyList(),
    val total: Double = 0.0
)
class ExpensesViewModel(private val repo:ExpenseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    //Vid 53
    private var allExpenses: MutableList<Expense> = mutableListOf()

    init {
        getAllExpenses()
    }

    //Vid 53
    private fun updateExpenseList() {
        allExpenses = repo.getAllExpenses().toMutableList()
        updateState()
    }

    private fun getAllExpenses(){
        //Vid 53
        repo.getAllExpenses()
        updateExpenseList()
    }

    fun addExpense(expense: Expense){
        repo.addExpense(expense)
        //Vid 53
        updateExpenseList()
    }

    fun deleteExpense(expense: Expense){
        repo.deleteExpense(expense)
        updateExpenseList()
    }

    fun editExpense(expense: Expense){
        repo.editExpense(expense)
        updateExpenseList()

    }

    private fun updateState(){
        _uiState.update { state ->
            state.copy(expenses = allExpenses,total = allExpenses.sumOf { it.amount } )
        }
    }

    fun getExpenseWithID(id:Long): Expense{
        return allExpenses.first {it.id == id}
    }

    //Vid 38
     fun getCategories():List<ExpenseCategory>{
         return repo.getCategories()
     }



}