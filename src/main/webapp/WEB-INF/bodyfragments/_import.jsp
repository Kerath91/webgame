Import data from XLS or XLSX file
<br>


	<div>
		<form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
			<table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td></td><td><input type="submit" value="Import" /></td></tr>
			</table>
		</form>
	</div>