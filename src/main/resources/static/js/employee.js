document.addEventListener("DOMContentLoaded", (event) => {
    fetch('http://localhost:8092/api/v1/employees')
    .then(response => {
        if (!response.ok) {
            throw new Error(`Could not fetch employee ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        let employeeList = "";
        for (let employeeData of data) {
            console.log(employeeData.birthdate.substring(0,4));
            console.log(employeeData.birthdate.substring(5,7));
            console.log(employeeData.birthdate.substring(8,10));

            let employee = "<tr>\n" +
                "<td>" + employeeData.lastName + ", " + employeeData.firstName + "</td>" +
                "<td>" + employeeData.birthdate.substring(8,10) + "-" + employeeData.birthdate.substring(5,7) + "-" + employeeData.birthdate.substring(0,4) + "</td>" +
                "<td>" + employeeData.documentType + " - " + employeeData.documentNumber + "</td>" +
                "<td>" + employeeData.emailAddress+ "</td></tr>";
            employeeList = employeeList + employee;
        }
        document.querySelector('#employees tbody').outerHTML =employeeList;


    })
        .catch(error => {
            console.error(error);
        })
})