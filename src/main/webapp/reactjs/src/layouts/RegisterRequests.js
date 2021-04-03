import React, {Component, useEffect, useState} from 'react';

const RegisterRequests = () => {

    const [requests, setRequests] = useState({});
    const [hasError, setErrors] =  useState(false);

    useEffect(() => {
        fetchData();
    }, []); // [] as second argument makes it load only once

    async function fetchData() {
        const res = await fetch("http://localhost:8080/allRegisteringRequests");
        res
            .json()
            .then(res => setRequests(res))
            .catch(err => setErrors(err));
    }



    return (
        <>
            <span>{JSON.stringify(requests)}</span>
        </>
    );
};

export default RegisterRequests;