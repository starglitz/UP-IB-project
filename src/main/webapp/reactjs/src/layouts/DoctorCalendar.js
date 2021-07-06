import {AuthenticationService} from "../services/AuthenticationService";
import {Calendar} from "../components/calendars/Calendar";

const DoctorCalendar = () => {
    return (
        <div style={{marginTop: '3%'}} className="content-box">
            <h1>View your appointments</h1>
            <Calendar />
        </div>
    )
}
export default DoctorCalendar;