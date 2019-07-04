import React, { Component } from 'react';
import PropTypes from "prop-types"
import {connect} from "react-redux"
import {createProject} from "../../actions/projectAction"
import classnames from "classnames"
import Feedback from 'react-bootstrap/Feedback';

class AddTask extends Component {

    constructor(){
        super();

        this.state = {
            project_name:"",
            project_identifier:"",
            description:"",
            start_date:"",
            end_date:"",
            errors:{}

        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    //life cycle hooks
    componentWillReceiveProps(nextProps) {
        if(nextProps.errors){
            this.setState({errors:nextProps.errors});
        }
    }

    onChange(e) {
        this.setState({ [e.target.name] : e.target.value});
    }

    onSubmit(e) {
        e.preventDefault();
        const newProject = {
            project_name: this.state.project_name,
            project_identifier: this.state.project_identifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        };
        this.props.createProject(newProject, this.props.history);
        
    }

    render() {
        const {errors} = this.state;
        return (
            <div>
                <h1>Add Task form</h1>
                <div className="project">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Create A Task</h5>
                                <hr />
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        <input type="text" 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid":errors.project_name
                                        })}
                                        // className="form-control form-control-lg"
                                        placeholder="Project Name" 
                                        name="project_name"
                                        value = {this.state.project_name}
                                        onChange = {this.onChange}
                                        />
                                        {errors.project_name && (
                                            <div className="invalid-feedback">{errors.project_name}</div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <input type="text" 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid":errors.projectIdentifier
                                        })}
                                        // className="form-control form-control-lg" 
                                        placeholder="Unique Project ID"
                                        name="project_identifier" 
                                        value = {this.state.project_identifier}
                                        onChange = {this.onChange}
                                        />
                                        {errors.projectIdentifier && (
                                            <div className="invalid-feedback">{errors.projectIdentifier}</div>
                                        )}
                                    </div>

                                    <div className="form-group">
                                        <textarea 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid":errors.description
                                        })}
                                        // className="form-control form-control-lg" 
                                        placeholder="Project Description" 
                                        name="description"
                                        value = {this.state.description}
                                        onChange = {this.onChange}
                                        ></textarea>
                                        {errors.description && (
                                            <div className="invalid-feedback">{errors.description}</div>
                                        )}
                                    </div>
                                    <h6>Start Date</h6>
                                    <div className="form-group">
                                        <input type="date" 
                                        className="form-control form-control-lg" 
                                        name="start_date" 
                                        value = {this.state.start_date}   
                                        onChange = {this.onChange} 
                                        />
                                    </div>
                                    <h6>Estimated End Date</h6>
                                    <div className="form-group">
                                        <input type="date" 
                                        className="form-control form-control-lg" 
                                        name="end_date" 
                                        value = {this.state.end_date}
                                        onChange = {this.onChange}
                                        />
                                    </div>

                                    <input type="submit" className="btn btn-primary btn-block mt-4" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                {/* <h2>{this.state.project_name}</h2> */}
            </div>

        );
    };
}

AddTask.propTypes = {
    createProject : PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state =>({
    errors:state.errors
});

export default connect(mapStateToProps,{ createProject }) (AddTask);