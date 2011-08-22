class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"expenseItem", action:"unprocessedList")
		"500"(view:'/error')
	}
}
