import axios from 'axios'

const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:8080/api/v1/clients';

class EmployeeService{

    getAllEmployees(){
        return axios.get(EMPLOYEE_BASE_REST_API_URL)
    }

    createEmployee(user){
        return axios.post(EMPLOYEE_BASE_REST_API_URL, user)
    }

    getEmployeeById(userId){
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/' + userId);
    }

    updateEmployee(userId, user){
        return axios.put(EMPLOYEE_BASE_REST_API_URL + '/' +userId, user);
    }

    deleteEmployee(userId){
        return axios.delete(EMPLOYEE_BASE_REST_API_URL + '/' + userId);
    }
}

export default new EmployeeService();