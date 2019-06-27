import React, { Component } from 'react';


class AddTask extends Component {

    constructor(){
        super();

        this.state = {
            project_Name:"",
            project_identifier:"",
            description:"",
            start_date:"",
            end_date:""

        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(e) {
        this.setState({ [e.target.name] : e.target.value});
    }

    onSubmit(e) {
        e.preventDefault();
        const newProject = {
            project_Name: this.state.project_Name,
            project_identifier: this.state.project_identifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        }
        
    }

    render() {
        return (
            <div className>
                <h1>Add Task form</h1>
                <div className="register">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Create A Task</h5>
                                <hr />
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        <input type="text" 
                                        className="form-control form-control-lg " 
                                        placeholder="Project Name" 
                                        name="project_name"
                                        value = {this.setState.project_Name}
                                        onChange = {this.onChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input type="text" 
                                        className="form-control form-control-lg" 
                                        placeholder="Unique Project ID"
                                        name="projectIdentifier" 
                                        value = {this.setState.project_identifier}
                                        onChange = {this.onChange}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <textarea className="form-control form-control-lg" 
                                        placeholder="Project Description" 
                                        name="projectDescription"
                                        value = {this.setState.description}
                                        onChange = {this.onChange}
                                        ></textarea>
                                    </div>
                                    <h6>Start Date</h6>
                                    <div className="form-group">
                                        <input type="date" 
                                        className="form-control form-control-lg" 
                                        name="start_date" 
                                        value = {this.setState.start_date}   
                                        onChange = {this.onChange} 
                                        />
                                    </div>
                                    <h6>Estimated End Date</h6>
                                    <div className="form-group">
                                        <input type="date" 
                                        className="form-control form-control-lg" 
                                        name="end_date" 
                                        value = {this.setState.end_date}
                                        onChange = {this.onChange}
                                        />
                                    </div>

                                    <input type="submit" className="btn btn-primary btn-block mt-4" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <h2>{this.state.project_Name}</h2>
            </div>

        );
    };
}

export default AddTask;