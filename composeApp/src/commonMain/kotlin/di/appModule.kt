package di


import com.exampleApp.db.AppDatabase
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import presentacion.ExpensesViewModel

//Vid 47
//Vid 52, appDatabase: AppDatabase
//Vid 65
fun appModule(appDatabase: AppDatabase) = module {
    //Vid 65 add HTTP
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase, get()) }
    factory { ExpensesViewModel(get()) }
}