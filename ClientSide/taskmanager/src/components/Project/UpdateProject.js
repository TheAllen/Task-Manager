import React, { Component } from "react";
import { getProject, createProject } from "../../actions/projectAction";
import PropTypes from "prop-types"
import { connect } from "react-redux"
import classnames from "classnames";


class UpdateProject extends Component {

    constructor() {
        super();
        //Takes the attributes of the project
        this.state = {
            id:"",
            project_name: "",
            project_identifier: "",
            description: "",
            start_date: "",
            end_date: "",
            errors: {}
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {

        if(nextProps.errors){
            this.setState({
                errors:nextProps.errors
            });
        }
        const { 
            id,
            project_name,
            project_identifier,
            description,
            start_date,
            end_date
        } = nextProps.project;

        this.setState({
            id,
            project_name,
            project_identifier,
            description,
            start_date,
            end_date
        });
    }

    componentDidMount() {
        //When it loads
        const { id } = this.props.match.params;
        this.props.getProject(id, this.props.history);
    }

    onChange(e) {
        this.setState(
            {[e.target.name]:e.target.value}
        )
    }

    onSubmit(e) {
        e.preventDefault();

        const updateProject = {
            id:this.state.id,
            project_name: this.state.project_name,
            project_identifier: this.state.project_identifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        };

        this.props.createProject(updateProject, this.props.history);
    }

    render() {
        const {errors} = this.state;
        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Update Project form</h5>
                            <hr />
                            <form onSubmit = {this.onSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid":errors.project_name
                                        })}
                                        placeholder="Project Name"
                                        name="project_name"
                                        value={this.state.project_name}
                                        onChange={this.onChange}
                                    />
                                    {
                                        errors.project_name && (
                                            <div className="invalid-feedback">{errors.project_name}</div>
                                        )
                                    }
                                </div>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control form-control-lg"
                                        placeholder="Unique Project ID"
                                        name="project_identifier"
                                        disabled
                                        value={this.state.project_identifier}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <textarea
                                        className="form-control form-control-lg"
                                        placeholder="Project Description"
                                        name="description"
                                        value={this.state.description}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input
                                        type="date"
                                        className="form-control form-control-lg"
                                        name="start_date"
                                        value={this.state.start_date}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input
                                        type="date"
                                        className="form-control form-control-lg"
                                        name="end_date"
                                        value={this.state.end_date}
                                        onChange={this.onChange}
                                    />
                                </div>

                                <input
                                    type="submit"
                                    className="btn btn-primary btn-block mt-4"
                                />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    project: state.project.project,
    errors: state.errors
});

export default connect(mapStateToProps, { getProject, createProject })(UpdateProject);