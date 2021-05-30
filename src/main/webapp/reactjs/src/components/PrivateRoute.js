import React from "react";
import { Redirect, Route } from "react-router-dom";
import { AuthenticationService } from "../services/AuthenticationService";

export const PrivateRoute = ({ component: Component, roles, ...rest }) => (

    <Route
        {...rest}
        render={(props) => {
            const role = AuthenticationService.getRole();
            if (!role) {
                return <Redirect to={{ pathname: "/login" }} />;
            }

            if (roles && !AuthenticationService.findCommonElement(role,roles)) {
                return <Redirect to={{ pathname: "/" }} />;
            }

            return <Component {...props} />;
        }}
    />
);
