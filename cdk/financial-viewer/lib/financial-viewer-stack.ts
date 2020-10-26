import * as core from '@aws-cdk/core';
import * as lambda from '@aws-cdk/aws-lambda'

export class FinancialViewerStack extends core.Stack {
  constructor(scope: core.Construct, id: string, props?: core.StackProps) {
    super(scope, id, props);

    const incomeStatementCsvReder = new lambda.Function(this, 'IncomeStatementCsvReader', {
      runtime: lambda.Runtime.JAVA_11,
      code: lambda.Code.fromAsset('../../backend/build/distributions/financial-viewer-1.0-SNAPSHOT.zip'),
      handler: 'com.azkz.lambda.function.IncomeStatementCsvReadHandler'
    })

  }
}
