import React, {Component, useEffect, useState} from 'react';
import GoogleMapReact from 'google-map-react';
import Button from "@material-ui/core/Button";
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';
import MyGreatPlace from "../layouts/my_great_place";


const Location = (props) => {

    const [center, setCenter] = useState({
        lat: 45.26,
        lng: 19.83
    });

    console.log(props.lat)
    console.log(props.lng)
    console.log(center)

    //  useEffect(() => {setCenter({ "lat": props.lat, "lng": props.lng })} ,[])
    console.log(center)

    const[zoom, setZoom] = useState(11)

    return (
        <>
            <div style={{ height: '500px', width: '700px', margin:'0 auto' }}>
                <GoogleMapReact
                    //bootstrapURLKeys={{ key: /* YOUR KEY HERE */ }}
                    defaultCenter={center}
                    defaultZoom={zoom}
                >
                    <MyGreatPlace
                        lat={props.lat}
                        lng={props.lng}
                        text=":D"/>


                </GoogleMapReact>
            </div>
        </>
    )


}

export default Location;