import React, {Component, useEffect, useState} from 'react';
import GoogleMapReact from 'google-map-react';
import Button from "@material-ui/core/Button";
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';


const Location = (props) => {

    const [center, setCenter] = useState({
        lat: props.lat,
        lng: props.lng
    });


    useEffect(() => {setCenter({ "lat": 45.01, "lng": 19.82 })} ,[])


    const[zoom, setZoom] = useState(11)

    return (
        <>
            <div style={{ height: '200px', width: '300px' }}>
                <GoogleMapReact
                    //bootstrapURLKeys={{ key: /* YOUR KEY HERE */ }}
                    defaultCenter={center}
                    defaultZoom={zoom}
                >
                    {/*<AnyReactComponent*/}
                    {/*    lat={59.955413}*/}
                    {/*    lng={30.337844}*/}
                    {/*    text="My Marker"*/}

                </GoogleMapReact>
            </div>
        </>
    )


}

export default Location;


// const containerStyle = {
//     position: 'relative',
//     width: '100%',
//     height: '100%'
// }
//
// export class MapContainer extends Component {
//
//
//
//     render() {
//         return (
//             <Map containerStyle={containerStyle} google={this.props.google}
//                  initialCenter={{
//                      lat: 40.854885,
//                      lng: -88.081807
//                  }}
//                  zoom={14}>
//
//
//                 <Marker
//                     name={'Dolores park'}
//                     position={{lat: 37.759703, lng: -122.428093}} />
//                 <Marker />
//
//                 {/*<Marker onClick={this.onMarkerClick}*/}
//                 {/*        name={'Current location'} />*/}
//
//                 {/*<InfoWindow onClose={this.onInfoWindowClose}>*/}
//                 {/*    <div>*/}
//                 {/*        <h1>{this.state.selectedPlace.name}</h1>*/}
//                 {/*    </div>*/}
//                 {/*</InfoWindow>*/}
//             </Map>
//         );
//     }
// }

// let api_key = 'AIzaSyBSy4OLNtk0u3zPO2s864I2_Gv4Ni1WeVw';
// export default GoogleApiWrapper({
//     apiKey: (api_key)
// })(MapContainer)