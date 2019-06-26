CREATE OR REPLACE PROCEDURE sp_select
(
    v_deptno   IN    employees.department_id%TYPE,
    employee_records    OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN employee_records FOR
    SELECT employee_id, first_name, salary,
            TO_CHAR(hire_date, 'YYYY-MM-DD') AS hiredate,
            department_name, city, e.department_id AS deptno
    FROM employees e INNER JOIN departments d ON e.department_id = d.department_id
                     INNER JOIN locations l ON d.location_id = l.location_id
    WHERE e.department_id = v_deptno;
END;
/