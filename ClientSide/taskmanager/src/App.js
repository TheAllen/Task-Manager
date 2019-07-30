import React, { Component } from 'react';
import {Button, Navbar, NavDropdown, Form, Nav, FormControl} from 'react-bootstrap';
import DashBoard from './components/dashboard'
import {BrowserRouter as Router, Route} from 'react-router-dom' 
import Header from './components/Layout/Header'
import logo from './logo.svg';
import './App.css';
import AddTask from './components/Project/AddTask';
import UpdateProject from './components/Project/UpdateProject';
import {Provider} from "react-redux";
import store from "./store";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard"
import AddProjectTask from './components/ProjectBoard/ProjectTasks/AddProjectTask';
import UpdateProjectTask from './components/ProjectBoard/ProjectTasks/UpdateProjectTask';
import Landing from './components/Layout/Landing'
import Register from './components/UserManagement/Register';
import Login from './components/UserManagement/Login';

import jwt_decode from 'jwt-decode'
import setJWT from './SecurityUtils/SetJWT'
import { SET_CURRENT_USER } from './actions/types';
import {logout} from './actions/SecurityActions';

const jwtToken = localStorage.jwtToken

if(jwtToken) {
  //Check token from localStorage and set it
  setJWT(jwtToken);
  const decode = jwt_decode(jwtToken);

  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decode
  });

  const currentTime = Date.now() / 1000;
  if(decode.exp < currentTime){

    //Handle logout
    store.dispatch(logout());
    window.location.href = "/";

    // window.location.href="/";
  }
}

class App extends Component {
  render() {
    
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header></Header>
            {
              //public routes
            }

            <Route exact path="/" component={Landing}></Route>
            <Route exact path="/register" component = {Register}></Route>
            <Route exact path="/login" component = {Login}></Route>
            {
              //private routes
            }
            <Route exact path="/dashboard" component={DashBoard}></Route>
            <Route exact path="/addTask" component={AddTask}></Route>
            <Route exact path="/updateProject/:id" component={UpdateProject}></Route>
            <Route exact path="/projectBoard/:id" component={ProjectBoard}></Route>
            <Route exact path="/addProjectTask/:id" component={AddProjectTask}></Route>
            <Route exact path="/updateProjectTask/:backlog_id/:pt_id/" component={UpdateProjectTask}></Route>
          </div>
        </Router>
      </Provider>
      
    );
  }
}

export default App;
