import Axios from 'axios';

const setJWT = token => {
    if(token) {
        Axios.defaults.headers.common["Authorization"] = token;
    } else {
        delete Axios.defaults.headers.common["Authorization"];
    }
}

export default setJWT;