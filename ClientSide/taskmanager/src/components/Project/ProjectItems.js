import React, { Component } from 'react';
import {Link} from 'react-router-dom';

export default class ProjectItems extends Component {
    render() {
        //Extract project prop
        const {project} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-5">
                    <div className="row">
                        <div className="col-2">
                            <span className="mx-auto">{project.project_identifier}</span>
                        </div>
                        <div className="col-lg-6 col-md-4 col-8">
                            <h3>{project.project_name}</h3>
                            <p>{project.description}</p>
                        </div>
                        <div className="col-md-4 d-none d-lg-block">
                            <ul className="list-group">
                                <a href="#">
                                    <li className="list-group-item board">
                                        <i className="fa fa-flag-checkered pr-1"> Task Board </i>
                                    </li>
                                </a>
                                <Link to={`/updateProject/${project.project_identifier}`}>
                                    <li className="list-group-item update">
                                        <i className="fa fa-edit pr-1"> Update Task Info</i>
                                    </li>
                                </Link>
                                <a href="">
                                    <li className="list-group-item delete">
                                        <i className="fa fa-minus-circle pr-1"> Delete Task</i>
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}