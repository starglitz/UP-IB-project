import {Component} from "react";
import {FormControl, FormHelperText, Input, InputLabel, makeStyles, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {ClinicService} from "../../services/ClinicService";

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: '25ch',
    },
}));

class NewClinic extends Component {

    async addClinic() {
        let name = document.getElementById("name").value
        let description = document.getElementById("description").value
        let address = document.getElementById("address").value
        let lat = document.getElementById("lat").value
        let lng = document.getElementById("lng").value

        const clinic = {
            'name': name,
            'description': description,
            'addressName': address,
            'lat': lat,
            'lng': lng
        }

        if (checkValid(name, description, address, lat, lng)) {
            console.log(clinic)
            await ClinicService.create(clinic)
                .then(() => alert("Clinic added"))
            window.location.reload();
        }

        function checkValid(name, description, address, lat, lng) {
            if (name === '' || description === '' || address === '' || lat === '' || lng === '') {
                alert("input all fields !")
                return false
            }
            return true
        }
    }

    checkValid = (name, description, address) => {
    }

    render() {
        return(
            <form className="form-size" method="POST" action="login">
                <h1><b>New clinic</b></h1>

                <TextField fullWidth label="Name" id="name" type="text" className="input-margin" variant="outlined" />
                <TextField fullWidth label="Description" id="description" type="text" className="input-margin" variant="outlined" />
                <TextField fullWidth label="Address" id="address" type="text" className="input-margin" variant="outlined" />
                <TextField fullWidth label="Lat coordinate" id="lat" type="text" className="input-margin" variant="outlined" />
                <TextField fullWidth label="Lng coordinate" id="lng" type="text" className="input-margin" variant="outlined" />

                <hr />
                <Button fullWidth size="large" color="inherit" onClick={this.addClinic}>
                    Submit
                </Button>
            </form>
        )
    }
}

export default NewClinic