import './App.css';
import HomeComponent from "./components/HomeComponent";

function App() {
  return (
    <div className="App">
        <Router>
            <HeaderComponent />
            <div className = "container">
                <Route path = "/" exact component = {HomeComponent}> </Route>
                <Route path = "/home" exact component = {HomeComponent}> </Route>
                <Route path = "/verEjemplo/:id" exact component = {HomeComponent}> </Route>
            </div>
        </Router>
    </div>
  );
}

export default App;
