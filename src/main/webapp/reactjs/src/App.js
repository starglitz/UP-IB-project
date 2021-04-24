import './App.css';
import Navbar from './components/navbar';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import RegisterLayout from "./layouts/RegisterLayout";
import NurseLayout from "./layouts/NurseLayout";
import PatientProfilLayout from "./layouts/PatientProfilLayout";
import RegisterRequests from "./layouts/RegisterRequests";
import RecipeLayout from "./layouts/RecipeLayout";
import NewAppointmentLayout from "./layouts/NewAppointmentLayout";
import LoginLayout from "./layouts/LoginLayout";
import AppointmentsTable from "./components/tables/AppointmentsTable";
import UpdateAppointment from "./components/updateAppointment";
import ClinicProfile from "./layouts/ClinicProfile";

function App() {
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>
                <Route path="/register" exact component={RegisterLayout}/>
                <Route path="/login" exact component={LoginLayout}/>
                <Route path="/nursePage" exact component={NurseLayout}/>
                <Route path="/profile" exact component={PatientProfilLayout}/>
                <Route path="/registerRequests" exact component={RegisterRequests}/>
                <Route path="/recipes" exact component={RecipeLayout}/>
                <Route path="/addAppointment" exact component={NewAppointmentLayout}/>
                <Route path="/appointments" exact component={AppointmentsTable}/>
                <Route path="/updateAppointment" exact component={UpdateAppointment}/>
                <Route path="/clinicProfile" exact component={ClinicProfile}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
