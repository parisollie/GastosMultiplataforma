package di


import com.exampleApp.db.AppDatabase
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.dsl.module
import presentacion.ExpensesViewModel

//Vid 47
//Vid 52, appDatabase: AppDatabase
fun appModule(appDatabase: AppDatabase) = module {
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase) }
    factory { ExpensesViewModel(get()) }
}