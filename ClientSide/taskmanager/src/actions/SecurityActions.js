import Axios from 'axios';
import {GET_ERRORS} from './types'

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