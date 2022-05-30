package br.edu.ifg.calculator;

import br.edu.ifg.proto.calculator.*;
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
    @Override
    public StreamObserver<MeanGeometricAverageRequest> meanGeometricAverage(StreamObserver<MeanGeometricAverageResponse> responseObserver) {
        StreamObserver<MeanGeometricAverageRequest> requestObserver = new StreamObserver<MeanGeometricAverageRequest>() {
            int a = 2;
            int b = 1;

            @Override
            public void onNext(MeanGeometricAverageRequest value) {
                a *= value.getNumber();
                b += 1;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                float a,b;
                a = 5;
                b = 5;

                float mg;
                mg= (float) Math.exp((Math.log((a*b)))/2);
                float average = mg;
                responseObserver.onNext(
                        MeanGeometricAverageResponse.newBuilder()
                                .setAverage(average)
                                .build()
                );
                responseObserver.onCompleted();
            }
        };

        return requestObserver;
    }
}

