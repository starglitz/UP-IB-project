import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';

const RegisterLayout = () => {

    const sendData = ()  => {
        let email = document.getElementById('email').value;
        let password = document.getElementById('password').value;
        let confirm = document.getElementById('confirm').value;
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let address = document.getElementById('address').value;
        let city = document.getElementById('city').value;
        let state = document.getElementById('state').value;
        let contact = document.getElementById('contact').value;
        let lbo = document.getElementById('lbo').value;

        if(validateForm(email,password,confirm,name,surname,address,city,state,contact,lbo)) {


        let user = {"email":email, "password":password, "name":name, "surname":surname,
        "address":address, "city":city, "state":state, "contact":contact, "lbo":lbo, "enabled":true};
        console.log(user);
        fetch('http://localhost:8080/registration', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
            .then(response => response.json())
            .then(user => {
                console.log('Success:', user);
            })
            .catch((error) => {
                console.error('Error:', error);
            });

        }
    }

        const validateForm = (email, password,confirm, name, surname, address, city, state, contact, lbo)  => {
            let ok = true;
            if(email === "" || password === ""  || confirm === "" || name === "" || surname === "" || address === ""
                || city === "" || state === "" || contact === "" || lbo === "") {
                ok = false;
                alert("Make sure to fill all fields!")
            }
            else if(password !== confirm) {
                ok = false;
                alert("Passwords don't match!")
            }

            else if(validateEmail(email) === false) {
                ok = false;
                alert("You have entered an invalid email address!")
            }

            return ok;
        }


        const validateEmail = (email)  => {
            let mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
            if(email.match(mailformat)) {
                return true;
            }
            return false;
        }


    return (
        <>
            <h1 style={{textAlign:'center', margin:'20px'}}>Register to our clinic</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">
                {/*<form method="post" className="register-form">*/}

                    <label htmlFor="email" className="label-register">Email:</label>
                    <input  id="email" type="text" placeholder="enter email here" className="input-register"/>

                    <label htmlFor="password" className="label-register">Password:</label>
                    <input id="password" type="password" placeholder="enter password here" className="input-register"/>

                    <label htmlFor="confirm" className="label-register">Confirm password:</label>
                    <input  id="confirm" type="password" placeholder="confirm password" className="input-register"/>

                    <label htmlFor="name" className="label-register">Name:</label>
                    <input  id="name" type="text" placeholder="enter your name here" className="input-register"/>

                    <label htmlFor="surname" className="label-register">Surname:</label>
                    <input  id="surname" type="text" placeholder="enter your surname here" className="input-register"/>

                    <label htmlFor="address" className="label-register">Home address:</label>
                    <input  id="address" type="text" placeholder="enter your address here" className="input-register"/>

                    <label htmlFor="city" className="label-register">City:</label>
                    <input id="city" type="text" placeholder="enter your city here" className="input-register"/>

                    <label htmlFor="state" className="label-register">State:</label>
                    <input id="state" type="text" placeholder="enter your state here" className="input-register"/>


                    <label htmlFor="contact" className="label-register">Contact:</label>
                    <input id="contact" type="text" placeholder="enter your phone number here" className="input-register"/>

                    <label htmlFor="lbo" className="label-register">LBO:</label>
                    <input id="lbo" type="text" placeholder="enter your LBO here" className="input-register"/>

                    <button onClick={sendData} className="submit-register">Submit</button>
                {/*</form>*/}
                </div>
            </div>
        </>
    );
};

export default RegisterLayout;
