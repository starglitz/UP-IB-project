import './App.css';
import Navbar from './components/navbar';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import { PrivateRoute } from "./components/PrivateRoute";
import RegisterLayout from "./layouts/RegisterLayout";
import NurseLayout from "./layouts/NurseLayout";
import PatientProfilLayout from "./layouts/PatientProfilLayout";
import RegisterRequests from "./layouts/RegisterRequests";
import RecipeLayout from "./layouts/RecipeLayout";
import NewAppointmentLayout from "./layouts/NewAppointmentLayout";
import LoginLayout from "./layouts/LoginLayout";
import AppointmentsTable from "./components/tables/AppointmentsTable";
import UpdateAppointment from "./components/updateAppointment";
import AppointmentReview from "./layouts/AppointmentReview";
import ClinicProfile from "./layouts/ClinicProfile";
import Clinics from "./components/clinics/ClinicsTable";
import ClinicProfilePatient from "./components/clinicProfilePatient/clinicProfile"
import ClinicAppointments from "./components/appointments/appointmentTable";
import DoctorTable from "./components/clinicDoctors/doctorTable";
import BookingPage from "./components/booking/bookingPage";
import PatientProfile from "./profile/PatientProfile";
import PatientsTable from "./components/patients/PatientsTable";
import Location from "./components/Location";
import RegisterStaff from "./layouts/RegisterStaff";
import ChangePassword from "./layouts/ChangePassword";


function App() {
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>
                <Route path="/register" exact component={RegisterLayout}/>
                <Route path="/login" exact component={LoginLayout}/>
                <Route path="/nursePage" exact component={NurseLayout}/>
                {/*<PrivateRoute*/}
                {/*    exact*/}
                {/*    path="/login"*/}
                {/*    component={PatientProfilLayout}*/}
                {/*    roles={["PATIENT"]}*/}
                {/*/>*/}
                <Route path="/profile" exact component={PatientProfilLayout}/>
                <Route path="/registerRequests" exact component={RegisterRequests}/>
                <Route path="/recipes" exact component={RecipeLayout}/>
                <Route path="/addAppointment" exact component={NewAppointmentLayout}/>
                <Route path="/appointments" exact component={AppointmentsTable}/>
                <Route path="/updateAppointment" exact component={UpdateAppointment}/>
                <Route path="/clinicProfile" exact component={ClinicProfile}/>
                <Route path="/clinics" exact component={Clinics}/>
                <Route path="/clinic/:id" exact component={ClinicProfilePatient}/>
                <Route path="/clinicAppointments/:id" exact component={ClinicAppointments}/>
                <Route path="/doctors/:clinicId/:date" exact component={DoctorTable}/>
                <Route path="/booking/:id" exact component={BookingPage}/>
                <Route path="/appointment/:id" exact component={BookingPage}/>
                <Route path="/appointmentReview" exact component={AppointmentReview}/>
                <Route path="/patientProfile" exact component={PatientProfile}/>
                <Route path="/patients" exact component={PatientsTable}/>
                <Route path="/mapsTest" exact component={Location}/>
                <Route path="/staffRegister" exact component={RegisterStaff}/>
                <Route path="/changePassword" exact component={ChangePassword}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
