# resilience4j
Demo project that uses circuit breaker, retry, and rate limiter

# Rate limiting @RateLimiter
- Is used to control the amount of requests in a specific time frame that the users can send in your api server.

## Advantages
- DDoS
- Equal access for all users. Prevents users from abusing and monopolizing your API server.
- Resource management prevents unnecessary processes of excessive requests.

## Rate limiting strategies
### Token bucket algorithm
- Where users have a bucket of tokens and that token will be used for every request they send in the server and the bucket will be replenished at a fixed rate.

## Configurations
### limit for period
- Number of requests that you will allow within the limit refresh period. For example 100
### limit refresh period
- Time window for counting the requests. For example 1 minute, within 1 minute 100 requests are only allowed anything exceeds after 1 minute will be blocked automatically.

### timeout duration
- After reaching the limit refresh period(100 request per 1 minute) how many seconds would you allow a requests to come in again and apply again the rate limit.

# Retry @Retry
- It is used to test whether the operation is totally a success/ failure or not.

## Retry workflow
1. The users sends a request.
2. The request might be a temporary failure due to network issues, overloaded server, or timeouts.
3. It will attemps to resend the request based on specified logic after a predefined delay and number of retry.
4. If the request succeeds after a retry we are all happy.
5. If the request failed after multiple retries based on your logic whether to notify the administrator etc...

## Advantages
### Fault tolerance
- If the retry is implemented our service will be reliable in case of temporary unwanted happenings not directly sending fail messages in users.

### Reduce error rates
- Reduces failed requests thus enhancing user experience.

### Proper error handling
- If our services are a total failure we can handle it properly and gracefully continuining our operations.

## Considerations
### Number of retry
- Control also the number of retry to avoid overloading the failing service.
- Number of retries to be tested during the retrying phase.

### Retry delay
- Should be progressively increasing to avoid overloading the failing service.
- Amount of time before retrying again a temporary failed request.

## Real world analogy
- Imagine you're online shopping and adding items to your cart. When you click "purchase," the system might attempt to connect to the payment processing service. If the initial connection fails due to a temporary network issue, retry logic can automatically resend the payment request a few times before displaying an error message to you. This approach increases the chances of a successful transaction despite a temporary hiccup.

# Circuit Breaker @CircuitBreaker
- Acts like an automatic switch when it detects a failing services it will be automatically managed by circuit breaker based on your configuration.

## State Transitions
### Closed
- Allow requests to flow/ send/ process.

### Open
- When circuit breaker experience a high number of failures within specific time frame. The state will be set in to open thus rejecting all incoming requests preventing more damage in your api.

### Half open
- This state acts like a tester whether for the circuit breaker to change its state to closed or open. It allows one request to come in after a specific time frame(wait duration) of being in open state(not accepting any requests) when the request succeeds it will be changed into closed thus allowing requests to flow again otherwise when that one requests fail again it will be open thus rejecting all requests to come in.

## Advantages
### Fault tolerance
- When a service keeps failing it isolates that service preventing anymore damage.

### Fast recovery
- When a service that keeps failing is now ready to be run again the circuit breaker will attempt to reconnect automatically.

### Reduce load
- In case of failure of service the circuit breaker will prevent any request to be sent in that service thus preventing an overload in failing service.

### Monitoring and Observability
- When the circuit breaker is enabled you can quickly identify what services keep failing with the help of state transitions.

## Real world analogy
Scenario: Electrical Circuit at Home
Closed State: Imagine your home's electrical circuit as the service protected by a circuit breaker. In the normal functioning state (closed state), the circuit breaker allows electricity (requests) to flow through the circuit and power your appliances. Lights turn on, electronics function properly, and everything works as expected.

Open State: Now, imagine overloading the circuit by plugging in too many appliances (causing failures). This excessive load trips the circuit breaker (open state). As a safety measure, the circuit breaker cuts off power (rejects requests) to the entire circuit to prevent overheating and potential damage. Your lights go out, and your electronics stop working.

Half-Open State: After a short period (wait duration), the circuit breaker might enter a half-open state. This is like cautiously trying to turn a single appliance back on (testing for recovery). If that single appliance works properly (successful request), it suggests the circuit can handle more load.

Reset to Closed State: If the single appliance functions well, it indicates the overload issue might be resolved. The circuit breaker resets to the closed state, allowing electricity to flow again (normal request flow resumes). You can now turn on other appliances progressively.

Back to Open State (if needed): However, if that single appliance in the half-open state also malfunctions (failed request), it suggests the circuit is still overloaded or faulty. The circuit breaker immediately trips back to the open state (rejection of requests) to prevent further issues. You'll need to identify the problematic appliance (fix the failing service) before resetting the breaker.

## Code
- When theres no name specified in @CircuitBreaker/ @Retry it will take the default configuration. Otherwise if theres a name specified the name should be match in application.properties configuration.
- You can define your fallback method when the request fails in both @CircuitBreaker and @Retry
- You can implement @CircuitBreaker and @Retry in your @FeignClient methods and @RateLimiter anywhere

# Related Links
[Rate limit, Circuit Breaker, and Retry](https://github.com/shabbirdwd53/resilience4j)
[Rate limiter deep dive](https://levelup.gitconnected.com/rate-limiting-in-spring-boot-52220ba272c6)
[Retry and Circuit Breaker using Feign Client together](https://github.com/nitsbat/circuit-breaker)
# The resilience4j will be in the caller of other api. For example the service a is calling the service b the resilience4j will be in service a the caller service.


# Other modules
- Bulkhead
- Timelimiter
- Cache


https://www.baeldung.com/java-feign-client-exception-handling
https://blog.devgenius.io/circuit-breaker-and-feign-client-implementation-in-spring-boot-3-1-zipkin-opentelemetry-46606aaded0c
https://stackoverflow.com/questions/63299493/spring-cloud-feign-client-with-hystrix-circuit-breaker-timeout-defaults-in-2-sec