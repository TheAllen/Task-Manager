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

class App extends Component {
  render() {
    
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header></Header>
            <Route exact path="/dashboard" component={DashBoard}></Route>
            <Route exact path="/addTask" component={AddTask}></Route>
            <Route exact path="/updateProject/:id" component={UpdateProject}></Route>
          </div>
        </Router>
      </Provider>
      
    );
  }
}

export default App;
