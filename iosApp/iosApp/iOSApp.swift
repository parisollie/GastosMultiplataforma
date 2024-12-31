import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    //Vid 47
    init(){
        MainViewControllerKt.doInitKoin()
    }
    
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
