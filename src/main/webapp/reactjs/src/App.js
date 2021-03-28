import './App.css';
import Navbar from './components/navbar';
import SampleLayout from './layouts/SampleLayout';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import RegisterLayout from "./layouts/RegisterLayout";
import NurseLayout from "./layouts/NurseLayout";

function App() {
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>
                <Route path="/samplePath" exact component={SampleLayout}/>
                <Route path="/register" exact component={RegisterLayout}/>
                <Route path="/nursePage" exact component={NurseLayout}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
