import Axios from 'axios';
import {GET_ERRORS, SET_CURRENT_USER} from './types'
import setJWT from '../SecurityUtils/SetJWT'
import jwt_decode from 'jwt-decode'

export const createNewUser = (newUser, history) => async dispatch => {
    try{
        await Axios.post("http://localhost:8080/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });

    }
    catch(e) {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });
    }
}

export const login = LoginRequest => async dispatch => {
    //Post request to login
    //extract token
    //Store the token in the local storage
    //Set tokens in the headers(Authorization) in order to do anything
    //decode token on React
    //dispatch to our security reducer

    try{
        const res = Axios.post("http://localhost:8080/api/users/login", LoginRequest);

        //Extracting token from res
        const {token} = res.data;

        localStorage.setItem('jwtToken', token);
        setJWT(token);

        //Decoded token
        const decode = jwt_decode(token);

        //set current user
        dispatch({
            type: SET_CURRENT_USER,
            payload: decode
        });

    }
    catch(e){
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });
    }
}