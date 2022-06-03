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
            int a = 1;
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
                float a,b,c;
                a = 6;
                b = 5;
                c = 7;

                float mg;
                mg= (float) Math.exp((Math.log((a*b*c)))/3);
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

