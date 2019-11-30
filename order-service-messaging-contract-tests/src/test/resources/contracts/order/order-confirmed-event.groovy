package contracts

org.springframework.cloud.contract.spec.Contract.make {
    name "OrderConfirmed Event"
    label 'order'
    
    input {
        triggeredBy('createOrder()')
    }
    
    outputMessage {
        sentTo 'orders'
        
        body([
            orderId: $(anyUuid()),
            paymentId: $(anyUuid()),
            amount: $(anyDouble()),
            street: $(anyNonBlankString()),
            city: $(anyNonBlankString()),
            state: $(regex('[A-Z]{2}')),
            zip: $(regex('[0-9]{5}')),
            country: $(anyOf('USA','Mexico'))
        ])
        
        headers {
            header('Content-Type', 'application/json')
        }
    }
}