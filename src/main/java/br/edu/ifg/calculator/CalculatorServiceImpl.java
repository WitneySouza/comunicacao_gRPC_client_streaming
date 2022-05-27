package br.edu.ifg.calculator;

import br.edu.ifg.proto.calculator.CalculatorServiceGrpc;
import br.edu.ifg.proto.calculator.SumRequest;
import br.edu.ifg.proto.calculator.SumResponse;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends
        CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse>
            responseObserver) {
        SumResponse sumResponse = SumResponse.newBuilder()
                .setSumResult(request.getFirstNumber() +
                        request.getSecondNumber())
                .build();
        responseObserver.onNext(sumResponse);
        responseObserver.onCompleted();
    }
}

