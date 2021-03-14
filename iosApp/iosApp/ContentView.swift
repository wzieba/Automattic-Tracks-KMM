import SwiftUI
import shared


struct ContentView: View {
    var body: some View {
        Text(TracksEvent(name: "test event").name)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
