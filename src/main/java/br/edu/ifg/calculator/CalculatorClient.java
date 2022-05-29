package br.edu.ifg.calculator;

import br.edu.ifg.proto.calculator.CalculatorServiceGrpc;
import br.edu.ifg.proto.calculator.SumRequest;
import br.edu.ifg.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");
        CalculatorClient main = new CalculatorClient();
        main.run();
    }
    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                        50052)
                .usePlaintext()
                .build();
        doUnaryCall(channel);
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
    private void doUnaryCall(ManagedChannel channel){
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub =
                CalculatorServiceGrpc.newBlockingStub(channel);
        SumRequest request = SumRequest.newBuilder()
                .setFirstNumber(40)
                .setSecondNumber(40)
                .build();
        SumResponse response = stub.sum(request);
        System.out.println(request.getFirstNumber() + " + " +
                request.getSecondNumber() + " = " + response.getSumResult());
    }
}
