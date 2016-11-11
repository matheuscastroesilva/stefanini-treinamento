App.controller('AgenteCtrl', function($scope, AgenteService, $route,
		$routeParams, $location, EnvioService) {
	$scope.agente = [];
	$scope.agenteEditar = {};

	$scope.notFound = false;
	AgenteService.list().then(function(data) {
		$scope.agente = data.data;
		if (data.data.length == 0) {
			$scope.notFound = true;
		}
	}, function(data) {
		console.log("data", data);
	});

	$scope.cadastrar = function(agente) {

		var data = {
			nome : agente.nome,
			tempoServico : agente.tempoServico,
			dataContratacao : agente.dataContratacao
		}

		AgenteService.create(data).then(function(data) {
			$location.path('/');
		});
	}

	

	$scope.deletar = function(item) {
		AgenteService.remove(item).then(function(data) {
			$route.reload();
		});
	}

	$scope.agenteEditar = EnvioService.getParametro();
	
	$scope.editar = function(agenteEditar) {
		$scope.agenteEditar = {};
		EnvioService.addParametro(agenteEditar);
		$location.path('/editarAgente');
	}

	$scope.atualizar = function(item) {
		AgenteService.update(item).then(function(data) {
			$location.path('/');
		});
	}
	
	$scope.myDataSource = {
		    chart: {
		        caption: "Harry's SuperMart",
		        subCaption: "Top 5 stores in last month by revenue",
		        numberPrefix: "$",
		        theme: "ocean"
		    },
		    data:[{
		        label: "Bakersfield Central",
		        value: "880000"
		    },
		    {
		        label: "Garden Groove harbour",
		        value: "730000"
		    },
		    {
		        label: "Los Angeles Topanga",
		        value: "590000"
		    },
		    {
		        label: "Compton-Rancho Dom",
		        value: "520000"
		    },
		    {
		        label: "Daly City Serramonte",
		        value: "330000"
		    }]
		};

});

