<html>
<head>
<title></title>
</head>
<body>


        <h1>Edit Employee data</h1>  
        <form method="post" action="../editsave">
        <table >    
        <tr>  
        <td></td>   
         <td><input type="hidden"  name="id" value="${command1.id}" /></td> 
         </tr>   
         <tr>    
          <td>Name : </td>   
          <td><input name="name"  value="${command1.name}"/></td>  
         </tr>    
         <tr>    
          <td>Salary :</td>    
          <td><input name="salary" value="${command1.salary}"/></td>  
         </tr>   
         <tr>    
          <td>Designation :</td>    
          <td><input name="designation" value="${command1.designation}"/></td>  
         </tr>   
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form> 
       </body>
</html>   