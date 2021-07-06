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
import DoctorPatientsTable from "./components/patients/DoctorPatientsTable";
import RegisterStaff from "./layouts/RegisterStaff";
import ChangePassword from "./layouts/ChangePassword";
import BlockUsers from "./layouts/BlockUsers";
import UpdateStaff from "./layouts/UpdateStaff";
import NewClinicAdmin from "./components/clinicCentreAdmin/NewClinicAdmin";
import NewClinic from "./components/clinics/NewClinic";
import PasswordLess from "./services/PasswordLessError";
import {TokenService} from "./services/TokenService";
import RateTables from "./components/ratings/RateTables";
import PasswordlessWithLink from "./services/PasswordlessWithLink";
import {AuthenticationService} from "./services/AuthenticationService";
import LinkNotValid from "./layouts/LinkNotValid";
import PatientAppointments from "./components/tables/PatientAppointments";
import BussinesReports from "./layouts/BussinesReports";
import PatientAppointmentHistory from "./layouts/PatientAppointmentHistory";

import PatientMedicalHistory from "./layouts/PatientMedicalHistory";



function App() {
    // TokenService.removeToken()
  return (
    <div className="App">
      <Navbar/>
        <Router>
            <Switch>

                <Route path="/register" exact component={RegisterLayout}/>
                <Route path="/login" exact component={LoginLayout}/>
                <Route path="/magic/" component={PasswordLess}/>
                <Route path="/magicWithLink/" component={PasswordlessWithLink}/>
                <Route path="/linkExpired" component={LinkNotValid}/>

                {/*<Route path="/nursePage" exact component={NurseLayout}/>*/}
                <PrivateRoute
                    exact
                    path="/nursePage"
                    component={NurseLayout}
                    roles={["NURSE"]}
                />

                {/*<Route path="/profile" exact component={PatientProfilLayout}/>*/}
                <PrivateRoute
                    exact
                    path="/profile"
                    component={PatientProfilLayout}
                    roles={["PATIENT"]}
                />
                {/*<Route path="/registerRequests" exact component={RegisterRequests}/>*/}
                <PrivateRoute
                    exact
                    path="/registerRequests"
                    component={RegisterRequests}
                    roles={["CLINIC_CENTRE_ADMIN"]}
                />
                {/*<Route path="/recipes" exact component={RecipeLayout}/>*/}
                <PrivateRoute
                    exact
                    path="/recipes"
                    component={RecipeLayout}
                    roles={['NURSE']}
                />
                {/*<Route path="/addAppointment" exact component={NewAppointmentLayout}/>*/}
                <PrivateRoute
                    exact
                    path="/addAppointment"
                    component={NewAppointmentLayout}
                    roles={['CLINIC_ADMIN']}
                />
                {/*<Route path="/appointments" exact component={AppointmentsTable}/>*/}
                <PrivateRoute
                    exact
                    path="/appointments"
                    component={AppointmentsTable}
                    roles={['CLINIC_ADMIN']}
                />
                {/*<Route path="/updateAppointment" exact component={UpdateAppointment}/>*/}
                <PrivateRoute
                    exact
                    path="/updateAppointment"
                    component={UpdateAppointment}
                    roles={['CLINIC_ADMIN']}
                />
                {/*<Route path="/clinicProfile" exact component={ClinicProfile}/>*/}
                <PrivateRoute
                    exact
                    path="/clinicProfile"
                    component={ClinicProfile}
                    roles={['CLINIC_ADMIN']}
                />
                {/*<Route path="/clinics" exact component={Clinics}/>*/}
                <PrivateRoute
                    exact
                    path="/clinics"
                    component={Clinics}
                    roles={['PATIENT']}
                />
                {/*<Route path="/clinic/:id" exact component={ClinicProfilePatient}/>*/}
                <PrivateRoute
                    exact
                    path="/clinic/:id"
                    component={ClinicProfilePatient}
                    roles={['PATIENT']}
                />
                {/*<Route path="/clinicAppointments/:id" exact component={ClinicAppointments}/>*/}
                <PrivateRoute
                    exact
                    path="/clinicAppointments/:id"
                    component={ClinicAppointments}
                    roles={['PATIENT']}
                />
                {/*<Route path="/doctors/:clinicId/:date" exact component={DoctorTable}/>*/}
                <PrivateRoute
                    exact
                    path="/doctors/:clinicId/:date"
                    component={DoctorTable}
                    roles={['PATIENT']}
                />
                {/*<Route path="/booking/:id" exact component={BookingPage}/>*/}
                <PrivateRoute
                    exact
                    path="/booking/:id"
                    component={BookingPage}
                    roles={['PATIENT']}
                />


                {/*<Route path="/appointmentReview" exact component={AppointmentReview}/>*/}
                <PrivateRoute
                    exact
                    path="/patient/appointment/review"
                    component={AppointmentReview}
                    roles={['DOCTOR']}
                />


                {/*<Route path="/appointmentReview" exact component={AppointmentReview}/>*/}
                <PrivateRoute
                    exact
                    path="/patient/appointments"
                    component={PatientAppointments}
                    roles={['DOCTOR']}
                />

                {/*<Route path="/patients" exact component={DoctorPatientsTable}/>*/}
                <PrivateRoute
                    exact
                    path="/patients"
                    component={DoctorPatientsTable}
                    roles={['DOCTOR']}
                />

                <PrivateRoute
                    exact
                    path="/patient/history"
                    component={PatientMedicalHistory}
                    roles={['DOCTOR']}
                />

                {/*<Route path="/staffRegister" exact component={RegisterStaff}/>*/}
                <PrivateRoute
                    exact
                    path="/staffRegister"
                    component={RegisterStaff}
                    roles={['CLINIC_ADMIN']}
                />
                {/*<Route path="/changePassword" exact component={ChangePassword}/>*/}
                <PrivateRoute
                    exact
                    path="/changePassword"
                    component={ChangePassword}
                    roles={['CLINIC_ADMIN','PATIENT','DOCTOR','NURSE','CLINIC_CENTRE_ADMIN']}
                />

//                 <Route path="/blockUsers" exact component={BlockUsers}/>
                <PrivateRoute
                    exact
                    path="/blockUsers"
                    component={BlockUsers}
                    roles={['CLINIC_ADMIN']}
                />
                  
//                 <Route path="/updateStaff/:id" exact component={UpdateStaff}/>
                <PrivateRoute
                    exact
                    path="/updateStaff/:id"
                    component={UpdateStaff}
                    roles={['CLINIC_CENTRE_ADMIN']}
                />

                <PrivateRoute
                    exact
                    path="/newClinic"
                    component={NewClinic}
                    roles={['CLINIC_CENTRE_ADMIN']}
                />

                <PrivateRoute
                    exact
                    path="/newClinicAdmin"
                    component={NewClinicAdmin}
                    roles={['CLINIC_CENTRE_ADMIN']}
                />

                <PrivateRoute
                    exact
                    path="/rate"
                    component={RateTables}
                    roles={['PATIENT']}
                />

                <PrivateRoute
                    exact
                    path="/businessReports"
                    component={BussinesReports}
                    roles={['CLINIC_ADMIN']}
                />
                <PrivateRoute
                    exact
                    path="/appointmentHistory"
                    component={PatientAppointmentHistory}
                    roles={['PATIENT']}
                />
            </Switch>
        </Router>
    </div>
  );
}

export default App;
