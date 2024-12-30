package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.ExpenseManager
import model.Expense
import model.ExpenseCategory
import presentacion.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpenseAmount
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

//Vid 28
@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview(){
    Box(modifier = Modifier.padding(16.dp)){
        ExpensesTotalHeader(total = 100.23)
    }
}

//Vid 28
@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview(){
    Box(modifier = Modifier.padding(16.dp)){
        AllExpensesHeader()
    }
}

//Vid 29
@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview(){
    Box(modifier = Modifier.padding(16.dp)){
        ExpensesItem(expense = ExpenseManager.fakeExpenseList[0],onExpenseClick = {})
    }
}

//Vid 30

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview(){
    ExpensesScreen(
        //Vid 33
        uiState = ExpensesUiState(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1772.23
        ),
        onExpenseClick = {})

}

//Vid 40

@Preview(showBackground = true)
@Composable
fun ExpensesAmountPreview(){
    ExpenseAmount(
        priceContent = 12.0,
        onPriceChange = {},
        keyboardController = LocalSoftwareKeyboardController.current)
}
