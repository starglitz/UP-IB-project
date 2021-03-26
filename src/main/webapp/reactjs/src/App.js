import './App.css';
import Navbar from './components/navbar';
import SampleLayout from './layouts/SampleLayout';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>
                <Route path="/samplePath" exact component={SampleLayout}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
