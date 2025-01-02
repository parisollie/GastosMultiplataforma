package previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.ExpenseManager
import presentacion.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader


@Preview
@Composable
fun ExpensesTotalHeaderPreview() {
    ExpensesTotalHeader(total = 100.0)
}

@Preview(showBackground = true)
@Composable
fun AllExpensesPreview() {
    AllExpensesHeader()
}

@Preview
@Composable
fun ExpensesItemPreview() {
    ExpensesItem(ExpenseManager.fakeExpenseList.first(), {})
}

@Preview
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState.Success(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1052.2
        ), onExpenseClick = {}, onDeleteExpense = {})
}
/*Vid 28
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
        onExpenseClick = {}, onDeleteExpense = {})

}

//Vid 40

@Preview(showBackground = true)
@Composable
fun ExpensesAmountPreview(){
    ExpenseAmount(
        priceContent = 12.0,
        onPriceChange = {},
        keyboardController = LocalSoftwareKeyboardController.current)
}*/
