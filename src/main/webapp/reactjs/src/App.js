import './App.css';
import Navbar from './components/navbar';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import RegisterLayout from "./layouts/RegisterLayout";
import NurseLayout from "./layouts/NurseLayout";
import PatientProfilLayout from "./layouts/PatientProfilLayout";
import RegisterRequests from "./layouts/RegisterRequests";
import RecipeLayout from "./layouts/RecipeLayout";
import NewAppointmentLayout from "./layouts/NewAppointmentLayout";

function App() {
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>
                <Route path="/register" exact component={RegisterLayout}/>
                <Route path="/nursePage" exact component={NurseLayout}/>
                <Route path="/profile" exact component={PatientProfilLayout}/>
                <Route path="/registerRequests" exact component={RegisterRequests}/>
                <Route path="/recipes" exact component={RecipeLayout}/>
                <Route path="/addAppointment" exact component={NewAppointmentLayout}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
