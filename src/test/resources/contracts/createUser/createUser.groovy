package contracts.createUser
import org.springframework.cloud.contract.spec.Contract

import com.jayway.jsonpath.JsonPath

Contract.make {
    description "Get AssetGraph of the Learning Asset"
    request {
        method POST()
        url (value("/user"))
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
        headers {  
            contentType(applicationJson())  
            }
        testMatchers{
            jsonPath('$.username', byType())
            jsonPath('$.password', byType())
            jsonPath('$.shoppingCart', byType())
            jsonPath('$.shoppingCart.itemList', byType())
        }
        body ('''{
        "username": "Srishti",
        "password": "Singh",
        "shoppingCart": {
            "itemList": []
            }
        }''')
        headers {
            header('''Content-Type''', applicationJson())
        }
    }
}