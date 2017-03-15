$(document).ready(function() {
	
			var data = {}
			data["query"] = $("#query").val();

			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "http://localhost:8080/agendou/",
				data : JSON.stringify(data),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					var trHTML = '';
					$.each(data.empresas, function(index, value) {
						trHTML += '<tr><td>' + value.cnpj + '</td><td>'
								+ value.razaoSocial + '</td></tr>';
					});

					$('#empresas').append(trHTML);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});

			$('#formCadastroEmpresaButton').click(function() {
				var empresa = {
						cnpj:$("#cnpj").val(),
						razaoSocial:$("#razaoSocial").val()
				};
				
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "http://localhost:8080/agendou/salvar",
					data: JSON.stringify(empresa),
					dataType : "json",
					success : function(empresa) {
						alert(empresa.cnpj);
						console.log(empresa.cnpj);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}

				});
			});
		});
