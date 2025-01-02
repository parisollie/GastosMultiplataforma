package presentacion

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

// Vid 65 sealed
sealed class ExpensesUiState {
    object Loading : ExpensesUiState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUiState()
    data class Error(val message: String) : ExpensesUiState()
}
//Vid 32,
class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {
    //Vid 65,ExpensesUiState.Loading
    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getExpenseList()
    }

    //Vid 65
    private fun getExpenseList() {
        viewModelScope.launch {
            try {
                val expenses = repo.getAllExpenses()
                _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    //Vid 65
    private suspend fun updateExpenseList() {
        try {
            val expenses = repo.getAllExpenses()
            _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })
        } catch (e: Exception) {
            _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error occurred")
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.addExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.editExpense(expense)
                //Vid 53
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    //Vid 65 y Vid 69
    fun deleteExpense(id: Long) {
        viewModelScope.launch {
            try {
                repo.deleteExpense(id)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun getExpenseWithID(id: Long): Expense? {
        //Vid 65
        return (_uiState.value as? ExpensesUiState.Success)?.expenses?.firstOrNull { it.id == id }
    }

    //Vid 38
    fun getCategories(): List<ExpenseCategory> {
        return repo.getCategories()
    }
}

/*Vid 32
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



}*/