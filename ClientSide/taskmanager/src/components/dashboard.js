import React, { Component } from 'react';
import { Button, Navbar, NavDropdown, Form, Nav, FormControl, NavItem, MenuItem } from 'react-bootstrap';
import ProjectItem from './Project/ProjectItems'
import Header from './Layout/Header'
import CreateTaskButton from './Project/CreateTaskButton'

class DashBoard extends Component {
    render() {
        return (
            <div>
                <div className="projects">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12">
                                <h1 className="display-4 text-center">Tasks and Todos</h1>
                                    <br />
                                        <CreateTaskButton/>
                                    <br />
                                <hr />
                                <ProjectItem />
                            </div>
                        </div>
                    </div>
                </div>

            </div>



        );
    }
}

export default DashBoard;