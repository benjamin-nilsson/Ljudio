import React, { useState, useEffect } from "react";
import { Button } from 'antd';
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import EmployeeService from '../services/EmployeeService'
import {Link, useNavigate} from "react-router-dom";


const BoardAdmin = () => {
    let navigate = useNavigate();
    const [content, setContent] = useState("");
    const [employees, setEmployees] = useState([])

    useEffect(() => {

        getAllEmployees();
    }, [])

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

    const deleteEmployee = (employeeId) => {
        EmployeeService.deleteEmployee(employeeId).then((response) =>{
            getAllEmployees();

        }).catch(error =>{
            console.log(error);
        })

    }

    useEffect(() => {
        UserService.getAdminBoard().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);

                if (error.response && error.response.status === 401) {
                    EventBus.dispatch("logout");
                    navigate("/")
                    window.location.reload();
                }
            }
        );
    }, []);

    return (
        <div className = "container">
            <div className = "card col-md-10 offset-md-3 offset-md-3">
            <h2 className = "text-center"> List Users </h2>
            <table className="table table-bordered table-striped " >
                <thead>
                <th> User Id </th>
                <th> UserName </th>
                <th> Email Id </th>
                <th> Actions </th>
                </thead>
                <tbody>
                {
                    employees.map(
                        employee =>
                            <tr key = {employee.id} >
                                <td> {employee.id} </td>
                                <td> {employee.username} </td>
                                <td>{employee.email}</td>
                                <td style={{
                                    display: "flex",
                                    justifyContent: "center",
                                    alignItems: "center"
                                }}>
                                    <Link className="btn btn-info" to={`/edit-employee/${employee.id}`} >Update</Link>
                                    <Button className = "btn btn-danger" onClick = {() => deleteEmployee(employee.id)}
                                            style = {{marginLeft:"10px"}}> Delete</Button>
                                </td>
                            </tr>
                    )
                }
                </tbody>
            </table>
            </div>
        </div>
    );
};

export default BoardAdmin;